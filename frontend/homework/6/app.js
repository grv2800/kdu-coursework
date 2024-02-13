const express=require("express");
const { use } = require("express/lib/application");
const users=require("./data/user");
const userApi=require("./routes/api/users");
const cors=require('cors');
const app=express();

app.use(cors());
app.use(express.json());
app.use("/api/post",userApi);

app.listen(5000,()=>{
    console.log('application started on port 5000');
})