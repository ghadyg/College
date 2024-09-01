import React from 'react'
import "../Teacher/Teacherheader.css"
import { useNavigate } from 'react-router-dom'
import {useAuth} from '../AuthContext'

export default function Teacherheader({user}) {
    const nav = useNavigate();
    const {logout} = useAuth();
  return (
    
    <div className='Header'>
        <div className='divBtn'>
            <button onClick={()=>nav('/ManageCourses')}>Courses</button>
        </div>
        <div className='divBtn'>
            <button onClick={()=>nav("/ManageGrades")}>Grade</button>
        </div>
        <div className='teacherName' onClick={()=>{
            logout();
            nav("/")
        }}></div>
                    
    </div>
  )
}
