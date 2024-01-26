import React from "react"
import "./index.css"
import {Lector} from "../lector";

export const DepartmentCard = ({department, updateLectorName}) => {
    return (
        <div className="department-card">
            <h2 className="title">{department.name}</h2>
            <div className="divider"></div>
            <div className="list">
                {department.lectors.map(lector=> (
                    <Lector key={lector.id + department.id} lector={lector} updateLectorName={updateLectorName} />
                ))}
            </div>
        </div>
    )
}