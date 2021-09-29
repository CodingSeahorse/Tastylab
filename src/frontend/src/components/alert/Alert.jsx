import React from "react";

import './Alert.scss'

const Alert = ({message,type}) => (
    <div className="alert-container">
        <div className= {`alert-container__image__${type}`}/>
        <span className="alert-container__message">{message}</span>
    </div>
)

export default Alert;