import React, { useState,useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import "./StudentManageGrades.css";
import Studentheader from '../Studentheader';
import { useAuth } from '../../AuthContext';

export default function StudentManageGrades() {
  const {user,getEmail}= useAuth();
  const [filter,setFilter]=useState("");
  const[data,setData] = useState(null);
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
          const result = await fetch(`http://localhost:8080/api/v1/student/courses/${getEmail()}`,requestOptions)
            if(!cancel)
            {
              
              result =await result.json()
              setData(result);
              console.log("refresh");
              
            }
          
        } catch (error) {
          console.log(error)
        }
      
    }
    let cancel = false;
    try {
      fetchData();	
    } catch (error) {
      console.log(error)
    }
    
    return()=>{
      cancel=true;
    }
  },[])
  return (
    <div>
      <div className='gradeWrapper'>
              <Studentheader credentials={user}/>
              <div className='container'>
                <div className='gradelblbanner'>
                  <label>Grades</label>
                  <input className='gradesearchInputStd' placeholder='Search' onChange={(e)=>(setFilter(e.target.value))}></input>
                  <div className="table">
		              <div className="table-header">
		              	<div className="header__item">Name</div>
		              	<div className="header__item">DOCTOR</div>
		              	<div className="header__item">GRADE</div>
		              </div>
		                <div className="table-content">
                      {data !=null && data.map((obj,index)=>{
                                      if(obj.course.includes(filter) || obj.teacher.includes(filter) )
                                        return(<div className="table-row" key={index}>		
                                        <div className="table-data">{obj.course}</div>
                                        <div className="table-data">{obj.teacher}</div>
                                        { obj.grade===0 && <div className="table-data">Not Graded</div>
                                        }
                                        { obj.grade!==0 && <div className="table-data">{obj.grade}</div>
                                        }
                                      </div>)
                                    })}	
		                </div>	
	                </div>
                </div>
              </div>
            </div>
          
      
    </div>
  )
}

