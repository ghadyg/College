import { jwtDecode } from "jwt-decode";
import { useContext } from "react";
import {  createContext, useEffect, useState } from "react";

const AuthContext = createContext({})

const AuthProvider = ({children}) =>{
    const [user,setUser] = useState(null);

    const setUserDetails = ()=> {
        let token = localStorage.getItem('token');
        if (token) {
            
            const decodedToken = jwtDecode(token);
            setUser({
                email: decodedToken.sub,
                roles: decodedToken.scopes
            });
        }
    }
    const getRole = ()=> {
        let token = localStorage.getItem('token');
        if (token) {
            
            const decodedToken = jwtDecode(token);
            return decodedToken.scopes;
        }
        return "";
    }

    const getEmail = ()=> {
        let token = localStorage.getItem('token');
        if (token) {
            
            const decodedToken = jwtDecode(token);
            return decodedToken.sub;
        }
        return "";
    }

    useEffect(()=>{
        
        setUserDetails();
    },[]

    )

    const login = async (usernameAndPassword)=>{
        return new Promise((resolve,reject)=>{
            const requestOptions = {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                },
                body: usernameAndPassword, // Replace with your actual request object structure
              };
            
              fetch("http://localhost:8080/api/v1/login", requestOptions).then(res=>{
                if(!res.ok)
                {
                    reject("Invalid credentials");
                    return;
                }
                    
                const jwtToken = res.headers.get('Authorization');
                 localStorage.setItem('token',jwtToken);
                 const decodedToken = jwtDecode(jwtToken);
                 setUser({
                     email: decodedToken.sub,
                     roles: decodedToken.scopes
                 })
                 resolve(decodedToken.scopes);
             }).catch(err=>reject(err))
         })
     }

    const logout = async()=>{
        localStorage.removeItem('token');
        setUser(null);
    }

    const isUserAuthenticated = ()=>{
        let token = localStorage.getItem('token');
        if(!token)
            return false;
        const decodedToken = jwtDecode(token);
        
        if(Date.now() > decodedToken.exp*1000){
            logout();
            return false;
        }
        return true;
    }
    return(
        <AuthContext.Provider value={{
            user,
            login,
            logout,
            isUserAuthenticated,
            setUserDetails,
            getRole,
            getEmail
        }}>{children}</AuthContext.Provider>
    )

}

export const useAuth = () => useContext(AuthContext);

export default AuthProvider;


