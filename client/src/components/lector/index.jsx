import React, { useState } from "react"
import "./index.css"

export const Lector = ({ lector, updateLectorName }) => {
    const [onMouse, setOnMouse] = useState(false);
    const [name, setName] = useState(lector.name || '');
    const [isEditing, setIsEditing] = useState(false);

    const handleNameChange = (e) => {
        setName(e.target.value);
    };

    const handleSubmit = () => {
        updateLectorName(lector.id, name);
        setIsEditing(false);
    };

    const handleCancelClick = () => {
        setIsEditing(false);
        setOnMouse(false);
    };

    return (
        <div className="block">
            <div className="item" onMouseOver={() => setOnMouse(true)} onMouseOut={() => setOnMouse(false)}>
                <div className="info">
                    {isEditing ? (
                        <>
                            <input className="input" type="text" value={name} onChange={handleNameChange} />
                            <button className="button" onClick={handleSubmit}> <i className="fa fa-check" style={{fontSize:21, color:'#4133B7'}}></i></button>
                            <button className="button" onClick={handleCancelClick}> <i className="fa fa-close" style={{fontSize:21, color:'#ffa39e'}}></i></button>
                        </>
                    ) : (
                        <div className="name">
                            {lector.name}
                            <div>
                            {onMouse ? (
                                <button className="button" onClick={() => setIsEditing(true)}>
                                    <i className="fa fa-pencil" style={{fontSize:21, color:'#4133B7'}}></i>
                                </button>
                            ) : (
                                <div className="but-gap"></div>
                            )}
                            </div>
                        </div>
                    )}
                    <div className="degree">
                        {lector.degree}
                    </div>
                </div>
            </div>
        </div>
    );
}