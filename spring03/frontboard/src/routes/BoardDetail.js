import { useState } from "react";
import { useParams } from "react-router-dom";

function BoardDetail () {
    const params = useParams();
    console.log(params.bno);

    return(
        <div className="container main">
            <h1>BoardDetail {params.bno}</h1>
        </div>
    );
}

export default BoardDetail;