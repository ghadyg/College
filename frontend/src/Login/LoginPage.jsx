import React, { useEffect,useRef } from 'react'
import "./LoginPage.css"
import { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';


export default function LoginPage() {
  const [isName, setIsName] = useState(false);
  const [isPass, setIsPass] = useState(false);
  const [seePass, setSeePass] = useState(false);

  const [placePassholder, setPassPlaceholder] = useState('Password');
  const [placeNameholder, setNamePlaceholder] = useState('Name');
  const navigate = useNavigate();
  const {user,login,getRole} = useAuth();

  const[credentials,setCredentials]=useState({
    name:"",
    password:""
  })
  const UserinputRef = useRef(null);
  const PassinputRef = useRef(null);

  useEffect(() => {
    const role = getRole();
    if(role)
    {
      console.log(role);
      if(role ==="TEACHER")
        navigate("/ManageCourses");
      else if(role ==="STUDENT")
        navigate("/ManageStudentCourse")
    }
    

    const Userinput = UserinputRef.current;
    const Passinput = PassinputRef.current;

    const checkAutofill = () => {
      if (Userinput && Passinput ) {
        
        if (Userinput.matches(':-internal-autofill-selected')) {
          
          setIsName(true);
          clearTimeout(intervalId);
        } 
        if (Passinput.matches(':-internal-autofill-selected')) {
          
          setIsPass(true)
        } 
      }
    };
    const intervalId = setInterval(checkAutofill, 50);
    return () => {
      clearTimeout(intervalId);
    };
  }, []);

  return (
    <div className='Main'>
      <form className='Login-container'>
        <div className='login-label'>
          <span >Login</span>
        </div>
        <div>
        {isName && <label className='lbl'>Name</label>}
          <input className='input-name effect-19' id="inputUser" ref={UserinputRef} placeholder={placeNameholder} 
          onFocus={()=>{
            setIsName(true)
            setNamePlaceholder("")
          }}
          onBlur={(e)=>
          {
            if(e.target.value.length>0)
                return
            setIsName(false)
            setNamePlaceholder("Name")
          }}
          onChange={(e)=>setCredentials({...credentials,name:e.target.value})}>
          </input>
        </div>
        <div  className='span'>
        <span></span>
        </div>
        <div>
        {isPass && <label className='lbl' >Password</label>}
          <input className='input-name effect-19' ref={PassinputRef} type={seePass ? 'text' : 'password'} placeholder={placePassholder} 
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
            <button className='btnSubmit' type='button' onClick={async()=>{
              try
              {
                
                const usernameAndPassword = JSON.stringify({
                  "email": credentials.name,
                  "password": credentials.password
                });

                login(usernameAndPassword).then(res=>{
                  console.log(res);
                   if(res ==="TEACHER")
                     navigate("/ManageCourses");
                   else if(res ==="STUDENT")
                     navigate("/ManageStudentCourse")
                }).catch(err=>console.log(err));

              }
            catch (error) {
              console.error('Error occurred while logging in:', error);
              alert('An error occurred while logging in. Please try again later.');
            }
            }}>SIGN IN</button>
        </div>
        {/* <div style={{padding: '0px 0px 5px 0px'}}>
          {isStudent && <span className='spanSignup' >Don't have an account?{'\u00A0'}<a href="/Signup" >Sign up</a> </span>}
          {!isStudent && <span className='spanSignup' >Don't have an account?{'\u00A0'}<a href="/TeacherSignup" >Sign up</a> </span>}
        </div> */}
      </form>
    </div>
  )
}
