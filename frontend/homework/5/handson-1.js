const os = require("os");
const fs = require("fs");
const http=require('http');
const { log } = require("console");

function getOSDetails() {
  return {
    HostName: os.hostname(),
    OperatingSystem: os.type(),
    Architecture: os.arch(),
    OSRelease: os.release(),
    Uptime: os.uptime(),
    CPUCores: os.cpus().length,
    TotalMemory: os.totalmem(),
    FreeMemory: os.freemem(),
    CurrentWorkingDirectory: process.cwd(),
  };
}

const osDetails = getOSDetails();

// Writing JSON string to a local file

const jsonContent = JSON.stringify(osDetails, null, 2);
fs.writeFile("osDetails.json", jsonContent, "utf8", (err) => {
  if (err) {
    console.log("Error writing file:");
    return console.log(err);
  } else {
    console.log("Json file has been saved");
    console.log(jsonContent);
  } 
});

const server = http.createServer((req, res) => {
    if (req.method === 'GET' && req.url === '/') {
        fs.readFile('osDetails.json', 'utf8', (err, data) => {
            if (err) {
                res.writeHead(500, { 'Content-Type': 'text/plain' });
                res.end('Internal Server Error');
                return;
            }

            res.writeHead(200, { 'Content-Type': 'text/plain' });
            res.write(`Here is my system information:\n`);
            res.end(data);
        });
    } else {
        res.writeHead(404, { 'Content-Type': 'text/plain' });
        res.end('Not Found');
    }
});

const PORT = process.env.PORT || 5000;

server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
