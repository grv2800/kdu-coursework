import React from 'react'
import { DataInterface } from './Interfaces'
import "./PersonHeader.css";
export function PersonHeader({data}:Readonly<{data:DataInterface}>) {
  return (
    <div className='personHeader'>
        <h1>{data.name}</h1>
        <h4>{data.fullName}</h4>
        <h2>{data.qualification}</h2>
    </div>
  )
}
