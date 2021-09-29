import React, {useState} from "react";
import FormInput from '../../form/input-form-fields/input-form-fields';
import MyButton from "../../button/mybutton";

import './login-form.scss'

import {Link, useHistory} from "react-router-dom";
import AuthenticationService from "../../../services/AuthenticationService";
import Alert from "../../alert/Alert";

const LoginForm = () => {
    const [user,setUser] = useState({
        username:"",
        password:"",
        loggedIn: false,
        showSuccessMessage: false,
        showErrorMessage: false,
        loginFailed: false,
        redirect: false
    })

    const history = useHistory()

    const handleChange = (e) => {
        setUser({
            ...user,
            [e.target.name]:e.target.value})
    }

    const onSubmit = (e) => {
        AuthenticationService
            .executeJwtAuthenticationService(user.username,user.password)
            .then(response => {
                AuthenticationService
                    .registerSuccessfulLoginForJwt(
                        user.username,
                        response.data)

                setUser({...user,
                    loggedIn: true,
                    showSuccessMessage: true,
                    loginFailed: false
                })
                setTimeout(() => {history.push("/home")},2000)
            }).catch(() => {
                setUser({...user,
                    loggedIn: false,
                    showSuccessMessage: false,
                    loginFailed: true
                })
            })
        e.preventDefault()
    }

    const showMessage = (loggedIn,showSuccessMessage, showFailedMessage) => {
        if(showSuccessMessage === true && loggedIn === true){
            return(
                <React.Fragment>
                    <Alert message="Successfully logged in!" type="success"/>
                </React.Fragment>
            )
        }
        if(showFailedMessage === true && loggedIn === false){
            return <Alert message="A error occurred. Log in failed!" type="failed"/>
        }
    }

    return(
        <div className="login__input-area">
            <div className="login__input-area__content">
                {showMessage(user.loggedIn,user.showSuccessMessage,user.showErrorMessage)}
                <h3 className="login__input-area__title">Login</h3>
                <form className="login__input-area__form" onSubmit={onSubmit} method="post">
                    <FormInput type="text" placeholder="Username" name="username" value={user.username} onChange={handleChange}/>
                    <FormInput type="password" placeholder="Password" name="password" value={user.password} onChange={handleChange}/>
                    <div className="login__input-area__btn">
                        <MyButton value="submit"/>
                        <p className="login__input-area__btn__switch">Don't have a account? <Link to="/welcome/signup">click here</Link></p>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default LoginForm;