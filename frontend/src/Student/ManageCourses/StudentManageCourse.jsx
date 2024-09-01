import React, { useState,useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import Studentheader from '../Studentheader';
import "./StudentManageCourse.css"
import Registercourses from '../Registercourses/Registercourses';
import { useAuth } from '../../AuthContext';

export default function StudentManageCourse() {
    
    const courses={name:"ANALYSE",teacher:"admin"};
    const {user,getEmail}= useAuth();
    const[fillCourses,SetFillCourses] = useState(null);
    const[refresh,Setrefresh] = useState(true);

    const [filter,setFilter]=useState("");

    useEffect(()=>{
      async function fetchData()
      {
        const requestOptions = {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${localStorage.getItem('token')}`
          }
          };
          try {
            const result = await fetch(`http://localhost:8080/api/v1/student/studentCourses/${getEmail()}`, requestOptions);
    
            if (!cancel && refresh) {
              const data = await result.json(); // Await the result to get JSON
              console.log(data);
              SetFillCourses(data); // Update the state
              Setrefresh(false);
              console.log(refresh);
            }
          } catch (error) {
            console.log(error)
          }
        
      }
      let cancel = false;
      fetchData();	
      return()=>{
        cancel=true;
      }
    },[refresh])
  return (
    <div>
      <div className='Wrapper'>
        <Studentheader credentials={user}/>
        <div className='container'>
          <div className='lblbanner'>
            <label>Register Courses</label>
            <input className='searchInputStd' placeholder='Search' onChange={(e)=>(setFilter(e.target.value))}></input>
          </div>
        {fillCourses != null && fillCourses.map(element => {
          if(element.course.includes(filter)  )//|| element[1].includes(filter)
              return <Registercourses key={element.course} courses={element} student={user.email} Setrefresh={Setrefresh} />
        })}
        </div>
      </div>
    </div>
  )
}
