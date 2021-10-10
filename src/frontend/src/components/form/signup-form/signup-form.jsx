import React, {useState} from "react";
import {Link, useHistory} from "react-router-dom";

import FormInput from '../input-form-fields/input-form-fields';
import MyButton from '../../button/mybutton';

import './signup-form.scss'
import '../../../pages/welcome/welcome.scss'

import AuthenticationService from "../../../services/AuthenticationService";
import SignupService from "../../../services/SignupService";
import Alert from "../../alert/Alert";

const SignUp = () => {
    const[user,setUser] = useState({
        firstName:"",
        lastName:"",
        email:"",
        gender:"",
        age: 0,
        username:"",
        password:"",
        confirmPassword:"",
        signupSuccess: false,
        showSuccessMessage: false,
        showErrorMessage: false
    });

    const history = useHistory()

    const handleChange = (e) => {
        setUser({
            ...user,
            [e.target.name]:e.target.value})
    }

    const handleChangeConfirm = (e) => {
        setUser({
            ...user,
            [e.target.name]:e.target.value})
    }

    const onSubmit = (e) => {
        e.preventDefault()

        const postForm = {
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            gender: user.gender,
            age: user.age,
            username: user.username,
            password: user.password
        }

        SignupService
            .signUpRequest(postForm)
            .then(
                response => {
                    AuthenticationService
                        .registerSuccessfulLoginForJwt(
                            user.username,
                            response.data)
                    setUser({
                        ...user,
                        showSuccessMessage: true,
                        signupSuccess:true,
                        showErrorMessage: false
                    })
                    setTimeout(() => {history.push("/home")},2000)
                })
            .catch(() => {
                setUser({
                    ...user,
                    showSuccessMessage: false,
                    signupSuccess:false,
                    showErrorMessage: true
                })
            })
    }

    const showMessage = (showErrorMessage,signupSuccess, showSuccessMessage) => {
        if(signupSuccess === true && showSuccessMessage === true){
            return(
                <React.Fragment>
                    <Alert
                        message="Successfully registered. Welcome !"
                        type="success"/>
                </React.Fragment>
            )
        }
        if(showErrorMessage === true && signupSuccess === false){
            return (
                <Alert
                    message="A error occurred. Request can't validate. Check your Form."
                    type="failed"/>)
        }
    }

    return(
        <div className="signup__input-area">
            <div className="signup__input-area__content">
                <h3 className="signup__input-area__title">Signup</h3>
                {showMessage(user.showErrorMessage,user.signupSuccess,user.showSuccessMessage)}
                <form className="signup__input-area__form"
                      onSubmit={onSubmit}>
                    <FormInput
                        type="text"
                        placeholder="FirstName"
                        name="firstName"
                        value={user.firstName}
                        onChange={handleChange}/>
                    <FormInput
                        type="text"
                        placeholder="LastName"
                        name="lastName"
                        value={user.lastName}
                        onChange={handleChange}/>
                    <FormInput
                        type="email"
                        placeholder="Email"
                        name="email"
                        value={user.email}
                        onChange={handleChange}/>
                    <FormInput
                        type="text"
                        placeholder="Gender"
                        name="gender"
                        value={user.gender}
                        onChange={handleChange}/>
                    <FormInput
                        type="number"
                        placeholder="Age"
                        name="age"
                        value={user.age}
                        onChange={handleChange}/>
                    <FormInput
                        type="text"
                        placeholder="Username"
                        name="username"
                        value={user.username}
                        onChange={handleChange}/>
                    <FormInput
                        type="password"
                        placeholder="Password"
                        name="password"
                        value={user.password}
                        onChange={handleChange}/>
                    <FormInput
                        type="password"
                        placeholder="Password Confirm"
                        name="confirmPassword"
                        value={user.confirmPassword}
                        onChange={handleChangeConfirm}/>
                    <div className="signup__input-area__btn">
                        <MyButton value="submit"/>
                        <div className="signup__input-area__btn__switch">
                            Already have a account?
                            <Link
                                to="/welcome/login"
                                className="click-here"
                                > click here</Link>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default SignUp;