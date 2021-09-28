import React from "react";

import FormInput from '../../form/input-form-fields/input-form-fields';
import MyButton from "../../button/mybutton";

import './login-form.scss'
import {Link} from "react-router-dom";

const LoginForm = () => (
    <div className="login__input-area">
        <div className="login__input-area__content">
            <h3 className="login__input-area__title">Login</h3>
            <form className="login__input-area__form">
                <FormInput type="text" placeholder="Username"/>
                <FormInput type="password" placeholder="Password"/>
            </form>
            <div className="login__input-area__btn">
                <MyButton value="submit"/>
                <p className="login__input-area__btn__switch">Don't have a account? <Link to="/welcome/signup">click here</Link></p>
            </div>
        </div>
    </div>
)

export default LoginForm;