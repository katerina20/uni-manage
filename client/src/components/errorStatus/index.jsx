import React from "react";
import "./index.css";

export const ErrorStatus = ({message}) => {
    return (
        <div className="error-container">
            <div className="error">
                Error: {message}
            </div>
        </div>
    );
}