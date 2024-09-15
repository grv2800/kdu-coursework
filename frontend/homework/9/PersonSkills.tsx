import { SkillInterface } from './Interfaces'
import "./personSkills.css";
export function PersonSkills({personskills}:Readonly<{personskills:SkillInterface[]}>) {
  return (
    <div className='personSkills'>
            <h2>Skills</h2>
            <ul>
                {personskills.map((skill) => (
                    <li key={skill.id}><strong>{skill.skill}</strong></li>
                ))}
            </ul>
    </div>
  )
}
