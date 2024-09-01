import React, { useState } from 'react'
import "./Registercourses.css"

export default function Registercourses({courses,student,Setrefresh}) {
  const [teacher,setTeacher] = useState(Object.keys(courses.teachers)[0] || '');
  return (
        <div className='Courses'>
        <div className='leftSide'>
            <label className='lblCourse'>{courses.course}</label>
            { !courses.taken &&
            <button className='btnRegister' onClick={async()=>{
              console.log(teacher);
              const reqBody ={
                student:student,
                teacher:teacher,
                course:courses.course
              }
              const requestOptions = {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                  'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(reqBody)
                };
              const response = await fetch(`http://localhost:8080/api/v1/student/register`,requestOptions)
              if(response.ok)
              {
                alert("Course Registered");
                Setrefresh(true);
              }
            }}>Register</button>}
            { courses.taken &&
            <button className='btnRegister' onClick={async()=>{
              const reqBody ={
                student:student,
                course:courses.course
              }
              const requestOptions = {
                method: 'DELETE',
                headers: {
                  'Content-Type': 'application/json',
                  'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(reqBody)
                };
              const response = await fetch(`http://localhost:8080/api/v1/student/enrollment`,requestOptions)
              if(response.ok)
              {
                alert("Course Unregistered");
                Setrefresh(true);
              }
            }}>Unregister</button>}
        </div>
        { !courses.taken &&
          <div className='lblT'>
            <label>Teached by: </label> 
            <select className='selectTeacher' onChange={(event)=>{
              setTeacher(event.target.value);
            }}>
              {Object.entries(courses.teachers).map(([email, name]) => (
            <option key={email} value={email}>
              {name}
            </option>
          ))}
            </select>
          </div>
        }
        { courses.taken &&
          <div className='lblT'>
            <label>Teached by: {Object.values(courses.teachers)}</label> 
          </div>

        }
                  
    </div>
    
  )
}
