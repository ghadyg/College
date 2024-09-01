import React from 'react'
import "./Signup.css"
import { useState } from 'react'

export default function Signup({isStudent}) {
  const [isName, setIsName] = useState(false);
  const [isEmail, setIsEmail] = useState(false);
  const [isPass, setIsPass] = useState(false);
  const [seePass, setSeePass] = useState(false);

  const [placePassholder, setPassPlaceholder] = useState('Password');
  const [placeNameholder, setNamePlaceholder] = useState('Name');
  const [placeEmailholder, setEmailPlaceholder] = useState('Email');

  const[credentials,setCredentials]=useState({username:"",password:"",email:""})
  return (
    <div>
      <div className='Main'>
      <form className='Signup-container'>
        <div className='Signup-label'>
          <span >Sign up</span>
        </div>
        <div>
        {isName && <label className='lbl'>Name</label>}
          <input className='input-name effect-19' placeholder={placeNameholder} 
          onFocus={(e)=>{
            if(e.target.value.length>0)
                return
            setIsName(true)
            setNamePlaceholder("")
          }}
          onBlur={()=>
          {
            setIsName(false)
            setNamePlaceholder("Name")
          }}
          onChange={(e)=>setCredentials({...credentials,username:e.target.value})}>
          </input>
        </div>
        <div  className='span'>
        <span></span>
        </div>

        <div>
        {isEmail && <label className='lbl'>Email</label>}
          <input className='input-name effect-19' type='email' placeholder={placeEmailholder} 
          onFocus={()=>{
            setIsEmail(true)
            setEmailPlaceholder("")
          }}
          onBlur={(e)=>
          {
            if(e.target.value.length>0)
            return
            setIsEmail(false)
            setEmailPlaceholder("Email")
          }}
          onChange={(e)=>setCredentials({...credentials,email:e.target.value})}>
          </input>
        </div>
        <div  className='span'>
        <span></span>
        </div>

        <div>
        {isPass && <label className='lbl' >Password</label>}
          <input className='input-name effect-19' type={seePass ? 'text' : 'password'} placeholder={placePassholder} 
          onFocus={()=>{
            setIsPass(true)
            setPassPlaceholder("")
          }}
          onBlur={(e)=>
          {
            if(e.target.value.length>0)
                return
            setIsPass(false)
            setPassPlaceholder("Password")
          }}
          onChange={(e)=>setCredentials({...credentials,password:e.target.value})}></input>
        </div>
        <div>
        <input
            type='checkbox'
            className='cbx'
            onMouseDown={(e) => {
              e.preventDefault();
              setSeePass(!seePass);
            }}
          />
          <label className='lbl' style={{color:'black'}}> Show Password</label>
        </div>
        <div style={{margin: '20px 0px'}}>
            <button className='btnSubmit' type='button' onClick={async () => {
              if(isStudent){
                  try{
                    const response = await fetch("http://localhost:8080/student",{
                      method:'POST',
                      headers:{'Content-Type': 'application/json',},
                      body:JSON.stringify(credentials)
                    })
                    if (response.ok) {
                      const data = await response.text(); 
                      alert(data); 
                    } else {
                      console.error('Failed to add student');
                      
                    }
                    
                    
                  }
                  catch (error) {
                    console.error('Error:', error);
                    // Handle network error
                  }
              }
              else {
                try{
                  console.log(credentials);
                  const response = await fetch("http://localhost:8080/teacher",{
                    method:'POST',
                    headers:{'Content-Type': 'application/json',},
                    body:JSON.stringify(credentials)
                  })
                  
                  if (response.ok) {
                    const data = await response.text(); 
                    alert(data); 
                  } else {
                    console.error('Failed to add teacher');
                    
                  }
                  
                  
                }
                catch (error) {
                  console.error('Error:', error);
                  // Handle network error
                }

              }
            }}>SIGN UP</button>
        </div>
      </form>
    </div>
    </div>
  )
}
