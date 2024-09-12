import React from 'react';

const RecommendationsPage = ({ items }) => {
    return (
        <div>
            <h1>Recommended Items</h1>
            <ul>
                {items.map((item, index) => (
                    <li key={index}>{item}</li>
                ))}
            </ul>
        </div>
    );
};

export default RecommendationsPage;
