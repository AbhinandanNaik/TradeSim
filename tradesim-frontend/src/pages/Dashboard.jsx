import React, { useEffect, useState } from 'react';
import { getAssets, buyAsset, refreshMarketData } from '../services/api';
import AssetCard from '../components/AssetCard';

function Dashboard() {
    const [assets, setAssets] = useState([]);
    const [loading, setLoading] = useState(true);
    const [userBalance, setUserBalance] = useState(10000);

    useEffect(() => {
        loadData();
    }, []);

    const loadData = async () => {
        setLoading(true);
        try {
            await refreshMarketData(); // Tell Java to fetch new prices
            const response = await getAssets(); // Get the list
            setAssets(response.data);
        } catch (error) {
            console.error("Failed to fetch assets:", error);
            // Don't alert immediately to avoid popup spam if backend is off
        } finally {
            setLoading(false);
        }
    };

    const handleBuy = async (asset) => {
        const quantity = prompt(`How many ${asset.name} do you want to buy?`);
        if (quantity && parseFloat(quantity) > 0) {
            try {
                // Hardcoded User ID 1 (TraderJoe)
                const result = await buyAsset(1, asset.symbol, parseFloat(quantity));
                alert(result.data);
            } catch (error) {
                alert("Trade Failed: " + (error.response?.data || error.message));
            }
        }
    };

    if (loading) return <div className="text-center mt-20 text-2xl">Loading Market Data...</div>;

    return (
        <div className="min-h-screen p-8">
            <header className="flex justify-between items-center mb-10">
                <h1 className="text-4xl font-extrabold text-gray-900">⚡ TradeSim Dashboard</h1>
                <div className="bg-white px-6 py-3 rounded-full shadow-sm border">
                    <span className="text-gray-500">Balance: </span>
                    <span className="text-green-600 font-bold text-xl">${userBalance.toLocaleString()}</span>
                </div>
            </header>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {assets.map((asset) => (
                    <AssetCard 
                        key={asset.symbol} 
                        asset={asset} 
                        onBuy={handleBuy} 
                    />
                ))}
            </div>
        </div>
    );
}

export default Dashboard;