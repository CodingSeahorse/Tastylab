import React from "react";
import './title.scss';

const Title = ({firstLine,secondLine,style}) => (
    <div className="my-title" style={style}>
        <div className="my-title__strip"/>
        <h2 className="my-title__title">{firstLine}</h2>
        <h2 className="my-title__title">{secondLine}</h2>
    </div>

)

export default Title;