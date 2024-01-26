import React, {useState, useEffect, useCallback} from "react"
import './index.css'
import axios from 'axios';
import {DepartmentCard} from '../departmentCard';
import {LoadingSpinner} from "../loadingSpinner";

export const DepartmentCardList = () => {
    const [departments, setDepartments] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const fetchData = useCallback(() => {
        setIsLoading(true);
        axios.get('http://localhost:8080/department')
            .then(response => {
                setDepartments(response.data);
                setIsLoading(false);
            })
            .catch(error => {
                setIsLoading(true);
                setErrorMessage(error.message);
        });
    }, [])

    useEffect(() => {
        fetchData();
    }, [fetchData]);

    const updateLectorName = (lectorId, newName) => {
        setIsLoading(true);
        axios.patch(`http://localhost:8080/lector/${lectorId}/change-name?newName=${newName}`)
            .then(_ => {
                fetchData();
            })
            .catch(error => {
                setIsLoading(false);
                setErrorMessage(error.message);
            });
    };

    return (
        <div className="department-container">
            {isLoading && <LoadingSpinner />}
            {errorMessage && <div className="error">{errorMessage}</div>}
            {departments.map(department => (
                <DepartmentCard
                    key={department.id + Math.random()}
                    department={department}
                    updateLectorName={updateLectorName} />
            ))}
        </div>
    )
}