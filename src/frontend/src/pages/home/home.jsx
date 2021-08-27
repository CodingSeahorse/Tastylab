import React from "react";
import './home.scss';

const Home = () => (
    <React.Fragment>
        <main className="landing">
            <div className="landing__inspiration">
                <div className="landing__inspiration__description-container">
                    <div className="landing__inspiration__description-container__top">
                        <span className="title-strip"/>
                        <h2>Find new inspirations</h2>
                            <p>Tastylab is a community platform where you
                            can easily <br/>share food with other people.
                            Fast and uncomplicated.<br/> It's anytime a
                            good idea to simplify your day routine with Tastylab. <br/>
                            At vero eos et accusam et justo duo dolores
                            et ea rebum.<br/> Stet clita kasd gubergren,
                            no sea takimata sanctus est <br/>Lorem ipsum
                            dolorsit amet.<br/></p>
                    </div>
                    <div className="landing__inspiration__description-container__bottom">
                        <p>Don’t miss a any news and <strong>follow us on social-media!</strong></p>
                        <button className="landing__inspiration__description-container__bottom__btn">facebook</button>
                        <button className="landing__inspiration__description-container__bottom__btn">instagram</button>
                        <button className="landing__inspiration__description-container__bottom__btn">twitter</button>
                        <button className="landing__inspiration__description-container__bottom__btn">pinterest</button>
                    </div>
                </div>
                <div className="landing__inspiration__card-container"/>
            </div>

            <div className="landing__highlight">
                <div className="landing__highlight__card-container"/>
                <div className="landing__highlight__description-container">
                    <div className="landing__highlight__description-container__top">
                        <span className="title-strip"/>
                        <h2>Tastylabs highlights</h2>
                        <p>Tastylab is a community platform where you
                            can easily <br/>share food with other people.
                            Fast and uncomplicated.<br/> It's anytime a
                            good idea to simplify your day routine with Tastylab. <br/>
                            At vero eos et accusam et justo duo dolores
                            et ea rebum.<br/> Stet clita kasd gubergren,
                            no sea takimata sanctus est <br/>Lorem ipsum
                            dolorsit amet.<br/></p>
                    </div>
                    <div className="landing__highlight__description-container__bottom">
                        <p>Don’t miss a any news and <strong>follow us on social-media!</strong></p>
                        <button className="landing__highlight__description-container__bottom__btn">facebook</button>
                        <button className="landing__highlight__description-container__bottom__btn">instagram</button>
                        <button className="landing__highlight__description-container__bottom__btn">twitter</button>
                        <button className="landing__highlight__description-container__bottom__btn">pinterest</button>
                    </div>
                </div>
            </div>
        </main>
    </React.Fragment>
)

export default Home;

//TODO: REPLACE BLUE BOX TO CAROUSEL