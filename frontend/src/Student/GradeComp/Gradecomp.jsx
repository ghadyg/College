import React,{useState} from 'react';
import './Gradecomp.css';

export default function Gradecomp({stdName,courseName,grade}) {
  const [inputGrade, setInputGrade] = useState(grade);
  return (
   
    <div className='contour'>
    <div className='gradeContainer'>
      <label className='studentName'>{stdName}</label>
      <div className='gradeDivMod'> 
      <input className='gradeInput' type='number' value={inputGrade} max="100"  onChange={(e)=>{
        if(e.target.value<0)
        setInputGrade(0)
        else if(e.target.value<=100)
          setInputGrade(e.target.value)
        else if(e.target.value>100)
          setInputGrade(100)
        
        }} onBlur={
        ()=>{
          const requestBody ={
            grade:inputGrade,
            studentEmail:stdName,
            course:courseName
          }
          const requestOptions = {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            body:JSON.stringify(requestBody)
          }
          try {
            
            fetch(`http://localhost:8080/api/v1/teacher/grade`,requestOptions)
          } catch (error) {
            
          }
           
        }
      }></input>
      </div>
      <label>{courseName}</label>
      </div>
    </div>
  )
}
