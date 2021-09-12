import React from "react";
import './login.scss';

import '../welcome.scss';

import FormInput from "../../../components/form-input/form-input";
import MyButton from "../../../components/button/mybutton";
import Title from "../../../components/title/title";

import '../../../helpers/_size-solver.scss';

const Login= () => (
    <React.Fragment>
        <main className="welcome">
            <div className="login">
                <div className="login__input-area">
                    <div className="login__input-area__content">
                        <h3 className="login__input-area__title">Login</h3>
                        <form className="login__input-area__form">
                            <FormInput type="text" placeholder="Username"/>
                            <FormInput type="password" placeholder="Password"/>
                        </form>
                        <div className="login__input-area__btn">
                            <MyButton value="submit"/>
                            <p className="login__input-area__btn__switch">Don't have a account? <a href="/">click here</a></p>
                        </div>
                    </div>
                </div>
                <aside className="login__description-container">
                    <Title firstLine="find new"
                           secondLine="inspirations"/>
                    <p className="login__description-container__text">
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
            </div>
        </main>
    </React.Fragment>
)

export default Login;