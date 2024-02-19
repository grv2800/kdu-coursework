import "./App.css";
import { PersonHeader } from "./PersonHeader";
import { DataInterface } from "./Interfaces";
import { PersonSkills } from "./PersonSkills";
import { PersonHobbies } from "./PersonHobbies";

function App() {
  const data: DataInterface = {
    name: "Amey",
    fullName: "Amey Aditya",
    qualification: "SSE",
    skills: [
      {
        id: 1,
        skill: "Python",
      },
      {
        id: 2,
        skill: "React",
      },
    ],
    hobbies: [
      {
        id: 1,
        hobby: "Cricket",
      },
    ],
  };
  return (
    <div className="container">
      <PersonHeader data={data} />
      <div className="sub-container">
        <PersonSkills personskills={data.skills} />
        <PersonHobbies personHobbies={data.hobbies} />
      </div>
    </div>
  );
}

export default App;
