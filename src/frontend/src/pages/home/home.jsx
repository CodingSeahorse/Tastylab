import React from "react";

import './home.scss';

import '../../helpers/_size-solver.scss'

const Home = () => (
    <React.Fragment>
        <main className="landing">
            {/* ========== INSPIRATION AREA ========== */}
            <div className="landing__inspiration">
                <div className="landing__inspiration__description-container">
                    <aside className="landing__inspiration__description-container__top">
                        <span className="title-strip"/>
                        <h2>
                            <p>Find new</p>
                            <p>inspirations</p>
                        </h2>

                        <p className="landing__inspiration__description-container__top__text">
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

                    <aside className="landing__inspiration__description-container__bottom">
                        <p>Don’t miss a any news and <strong>follow us on social-media!</strong></p>

                        <div className="landing__inspiration__description-container__bottom__btn">
                            <input
                                className="landing__inspiration__description-container__bottom__btn__facebook"
                                type="button"/>
                            <input
                                className="landing__inspiration__description-container__bottom__btn__instagram"
                                type="button"/>

                            <input
                                className="landing__inspiration__description-container__bottom__btn__twitter"
                                type="button"/>
                            <input
                                className="landing__inspiration__description-container__bottom__btn__pinterest"
                                type="button"/>
                        </div>
                    </aside>
                </div>

                <div className="landing__inspiration__card-container"/>{/*TODO: DELETE IT SOON !!!*/}

                <input
                    className="nav-arrow"
                    type="button"/>

            </div>

            {/* ========== HIGHLIGHT AREA ========== */}
            <div className="landing__highlight">

                <div className="landing__highlight__card-container"/> {/*TODO: DELETE IT SOON !!!*/}

                <div className="landing__highlight__description-container">
                    <aside className="landing__highlight__description-container-top">
                        <span className="title-strip"/>
                        <h2>
                            <p>Tastylabs</p>
                            <p>highlights</p>
                        </h2>

                        <p className="landing__highlight__description-container-top__text">
                           <p>
                                Tastylab is a community platform where you<br/>
                                can easily share foodwith other people.<br/>
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
            </div>
        </main>
    </React.Fragment>
)

export default Home;

//TODO: REPLACE BLUE BOX TO CAROUSEL