import React from 'react'
import "./ListItem.css";
interface IlistItem{
  text:string
}
export  function ListItem({text}:Readonly<IlistItem>) {
  return( 
  <div className='list-div'>
    <li>{text}</li>
    <img src="cross.png" alt="" />
    </div>
  )
}
