import axios from 'axios';
import React, { useState } from 'react'

const Login = ({setAuthToken}) => {
    const[user,setUser] = useState({username:"",password:""});

    const handleChange = (e) => {
        setUser({...user,[e.target.name]:e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try{
            const res = await axios.post("http://localhost:4040/auth/login",user);
            localStorage.setItem("token",res.data.token);
            setAuthToken(res.data.token);
        }catch(error){
            alert("Login Failed!");
        }
    };

  return (
    <>
        <form onSubmit={handleSubmit}>
            <input type='text' name='username' onChange={handleChange} placeholder='UserName' required/>
            <input type='text' name='password' onChange={handleChange} placeholder='PassWord' required/>
            <button type='submit'>Login</button>
        </form>
    </>
  )
}

export default Login;
