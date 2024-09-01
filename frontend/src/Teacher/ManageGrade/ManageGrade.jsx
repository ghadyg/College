import React,{ useState,useEffect } from 'react';
import "./ManageGrade.css";
import { useLocation } from 'react-router-dom';
import Teacherheader from '../Teacherheader';
import Gradecomp from '../../Student/GradeComp/Gradecomp';
import { useAuth } from '../../AuthContext';


export default function ManageGrade() {
    
  const {user,getEmail}= useAuth();
    const[fillCourses,SetFillCourses] = useState(null);
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
          const result = await fetch(`http://localhost:8080/api/v1/teacher/teacherCourses/${getEmail()}`,requestOptions)
          if(!cancel)
            {
              result =await result.json()
              SetFillCourses(result);  
            }

        } catch (error) {
          console.error(error)
        }
      
          
      
      }
      let cancel = false;
      try {
        fetchData();	
      } catch (error) {
        console.log(error);
      }
      
      return()=>{
        cancel=true;
      }
    },[])

    return (
        <div>
          <div className='WrapperGrade'>
                <Teacherheader credentials={user}/>
                <div className='contentGrade'> 
                 <label className='lblGradeBook'>Gradebook</label> 
                 <input className='searchInput' placeholder='Search' onChange={(e)=>(setFilter(e.target.value))}></input>
                 <label className='lblGrades'>Grades</label>
                 <div className='tableGrade'>
                  {
                    fillCourses !=null && fillCourses.map((obj,index)=>{
                      if(obj.course.includes(filter) || obj.student.includes(filter) )
                        return <Gradecomp key={index} courseName={obj.course} stdName={obj.student} grade={obj.grade}/>
                    })
                  }
                  
                 </div>
                 
                </div>
            </div>
            
          
        </div>
      );
}
