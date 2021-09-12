import React from "react";

import './registration.scss';
import '../welcome.scss';
import '../../../helpers/_size-solver.scss'

import FormInput from "../../../components/form-input/form-input";
import Mybutton from "../../../components/button/mybutton";
import Title from "../../../components/title/title";

const Registration = () => (
    <React.Fragment>
        <main className="welcome">
            <aside className="registration__description-container">
                <Title firstLine="find new"
                       secondLine="inspirations"/>
                <p className="registration__description-container__text">
                    <p>
                        Tastylab is a community platform where you<br/>
                        can easily share food with other people.<br/>
                        You will bring your cooking skills to a next level.<br/>
                        Lorem ipsum dolor sit amet, consetetur sadipscing<br/>
                        elitr, sed diam yam erat, sed diam voluptua.<br/>
                    </p>
                    <p>
                        At vero eos et accusam et justo<br/>
                        duo dolores et ea rebum. Stet clita kasd gubergren,<br/>
                        no sea takimata sanctus est Lorem ipsum dolor<br/>
                        sit amet.
                    </p>
                    <p>
                        duo dolores et ea rebum. Stet clita kasd gubergren,<br/>
                        no sea takimata sanctus est Lorem ipsum dolor sit<br/>
                        amet.
                    </p>
                </p>
            </aside>
            <div className="registration__input-area">
                <div className="registration__input-area__content">
                    <h3 className="registration__input-area__title">Registration</h3>
                    <form className="registration__input-area__form">
                        <FormInput
                            type="text"
                            placeholder="FirstName"/>
                        <FormInput type="text" placeholder="LastName"/>
                        <FormInput type="email" placeholder="Email"/>
                        <FormInput type="text" placeholder="Username"/>
                        <FormInput type="password" placeholder="Password"/>
                        <FormInput type="password" placeholder="Password Confirm"/>
                    </form>
                    <div className="login__input-area__btn">
                        <Mybutton value="submit"/>
                        <p className="login__input-area__btn__switch">Already have a account? <a href="/">click here</a></p>
                    </div>
                </div>
            </div>
        </main>
    </React.Fragment>
)

export default Registration;