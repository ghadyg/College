import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import "./ManageCourses.css"
import Teacherheader from '../Teacherheader';
import { useAuth } from '../../AuthContext';

export default function ManageCourses() {


	const [courses,setCourses]=useState(null);
	const[courseName,setCourseName]=useState("");
	const [deleteCourse,setDeleteCourse] = useState(null);
	const {user}= useAuth();
	
	const saveCourse = async ()=>{
		if(courseName.length==0) 
		{
			alert("please enter a course name");
			return;
		}
		const requestBody ={
			teacher:user.email,
			course:courseName,
			description:""
		}
		const requestOptions = {
			method: 'PUT',
			headers: {
			  'Content-Type': 'application/json',
			  'Authorization': `Bearer ${localStorage.getItem('token')}`
			},
			body:JSON.stringify(requestBody)
		  };
		  try {
			const response = await fetch(`http://localhost:8080/api/v1/teacher/course`,requestOptions)
			if(response.ok){
				alert("course added");
				fetchData();
			}		
		  } catch (error) {
			console.error('An error occurred while fetching:', error);
		  }

		
	}
	async function fetchData()
	{
		try
		{
			const requestOptions = {
    		    method: 'GET',
    		    headers: {
    		      'Content-Type': 'application/json',
				  'Authorization': `Bearer ${localStorage.getItem('token')}`
    		    }
    		  };
			  try {
				const result = await fetch("http://localhost:8080/api/v1/teacher/allCourses",requestOptions)
				if(result.ok)
					{
						result =await result.json();
						setCourses(result);
					}
			  } catch (error) {
				
			  }
			
		}
		catch(err)
		{
			console.log(err);
		}
	}
	useEffect(()=>{
		const handleClickOutside = (event) => {
			
			if (!event.target.closest('table')) {
				setDeleteCourse((prev)=>(prev=null));
				
				const cells = document.querySelectorAll('tbody tr');
				cells.forEach(cell => cell.classList.remove('selected'));
			}
		};
	
		// Attach event listener to handle clicks outside the table
		document.addEventListener('click', handleClickOutside);
		
		fetchData();	
		return()=>{
			
			document.removeEventListener('click', handleClickOutside);
		}
	},[])
    return (
        <div>
         
            <div className='Wrapper'>
                <Teacherheader user={user}/>
				
                <div className='content'>
				<div className='addCourse'>
					<input className='inputcourse' onChange={(e)=>(setCourseName(e.target.value))}></input>
					<button className='btn' onClick={saveCourse}>ADD COURSE</button>
				</div>
                <table id='courseTable'>
		<thead>
			<tr>
				<th>Course</th>
				<th>Teacher</th>
			</tr>
		</thead>
		<tbody onClick={(e)=>{
			 const cells = document.querySelectorAll('tbody tr');
			 cells.forEach(cell => cell.classList.remove('selected'));

			const cell =e.target.closest('tr');
			cell.classList.add('selected')
			
			const data = e.target.closest('td');
			const row =data.parentElement.innerHTML.replaceAll("<td>","").replaceAll("</td>",",");
			var arr = row.split(',');
			setDeleteCourse((prev)=>(prev={name:arr[0],course:arr[1]}));
			 
			
			
		}}>
			{courses!=null && courses.map((value,key)=>{
				return(<tr key={key}><td>{value.name}</td><td>{value.teacher.join(" , ")}</td></tr>);
			})}
		</tbody>
	</table>
              </div>
            </div>
        </div>
      );
    
}
