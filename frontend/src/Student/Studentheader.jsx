import React from 'react'
import "../Teacher/Teacherheader.css"
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../AuthContext';

export default function Studentheader({credentials}) {
    const nav = useNavigate();
    const {logout} = useAuth();
  return (
    <div className='Header'>
    <div className='divBtn'>
        <button onClick={()=>nav('/ManageStudentCourse')}>Register Courses</button>
    </div>
    <div className='divBtn'>
        <button onClick={()=>nav("/ManageStudentGrades")}>See Grades</button>
    </div>
    <div className='teacherName' onClick={()=>{
            logout();
            nav("/")
        }}></div>
                
</div>
  )
}
