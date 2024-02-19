import React from 'react'
import { HobbiesInterface } from './Interfaces'
import "./personHobbies.css";
export function PersonHobbies({personHobbies}:Readonly<{personHobbies:HobbiesInterface[]}>) {
  return (
    <div className='personHobbies'>
        <h2>Hobbies</h2>
        <ul>
                {personHobbies.map((hobby) => (
                    <li key={hobby.id}><strong>{hobby.hobby}</strong></li>
                ))}
        </ul>
    </div>
  )
}
