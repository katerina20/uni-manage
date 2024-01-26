import React, {useState, useEffect, useCallback} from "react"
import './index.css'
import axios from 'axios';
import {DepartmentCard} from '../departmentCard';
import {LoadingSpinner} from "../loadingSpinner";
import {ErrorStatus} from "../errorStatus";

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
    }, []);

    useEffect(() => {
        fetchData();
    }, [fetchData]);

    useEffect(() => {
        const timer = setTimeout(function () {
            setErrorMessage('');
        }, 4000);

        return () => clearTimeout(timer);
    }, [errorMessage]);

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
            {errorMessage && <ErrorStatus message={errorMessage} />}
            {departments.map(department => (
                <DepartmentCard
                    key={department.id}
                    department={department}
                    updateLectorName={updateLectorName} />
            ))}
        </div>
    )
}