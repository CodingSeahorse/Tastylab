import React from "react";

import FormInput from '../input-form-fields/input-form-fields';
import MyButton from '../../button/mybutton';

import './signup-form.scss'
import {Link} from "react-router-dom";

const SignUp = () => (
    <div className="signup__input-area">
        <div className="signup__input-area__content">
            <h3 className="signup__input-area__title">Signup</h3>
            <form className="signup__input-area__form">
                <FormInput
                    type="text"
                    placeholder="FirstName"/>
                <FormInput type="text" placeholder="LastName"/>
                <FormInput type="email" placeholder="Email"/>
                <FormInput type="text" placeholder="Username"/>
                <FormInput type="password" placeholder="Password"/>
                <FormInput type="password" placeholder="Password Confirm"/>
            </form>
            <div className="signup__input-area__btn">
                <MyButton value="submit"/>
                <p className="signup__input-area__btn__switch">Already have a account? <Link to="/welcome/login">click here</Link></p>
            </div>
        </div>
    </div>
)

export default SignUp;