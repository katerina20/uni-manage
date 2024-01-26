import React from "react"
import "./index.css"
import {Lector} from "../lector";

export const DepartmentCard = ({department, updateLectorName}) => {
    return (
        <div className="department-card">
            <h2 className="title">{department.name}</h2>
            <div className="divider"></div>
            {department.lectors.map(lector=> (
                <Lector key={lector.id} lector={lector} updateLectorName={updateLectorName} />
            ))}
        </div>
    )
}