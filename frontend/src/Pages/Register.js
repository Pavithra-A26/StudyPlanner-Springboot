import React, { useState } from 'react'
import axios from "axios";

const Register = () => {
    const [user,setUser] = useState({username:"",password:""});

    const handleChange = (e) => {
        setUser({...user,[e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await axios.post("http://localhost:4040/auth/register",user);
            alert("User registered successfully!");
        }catch(error){
            alert("REgistered Failed!");
        }
    };
  return (
    <>
        <form onSubmit={handleSubmit}>
            <input type='text' name='username' onChange={handleChange} placeholder='UserName' required/>
            <input type='text' name='password' onChange={handleChange} placeholder='PassWord' required/>
            <button type='submit'>Register</button>
        </form>
    </>
  )
}

export default Register;
