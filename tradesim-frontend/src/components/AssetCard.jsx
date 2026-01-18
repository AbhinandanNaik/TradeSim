import React from 'react';

function AssetCard({ asset, onBuy }) {
    // asset contains: { symbol, name, currentPrice, imageUrl }
    
    return (
        <div className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow border border-gray-100">
            <div className="flex items-center space-x-4 mb-4">
                <img src={asset.imageUrl} alt={asset.name} className="w-12 h-12 rounded-full" />
                <div>
                    <h3 className="text-xl font-bold text-gray-800">{asset.name}</h3>
                    <p className="text-gray-500 uppercase">{asset.symbol}</p>
                </div>
            </div>

            <div className="mb-6">
                <p className="text-3xl font-bold text-blue-600">
                    ${asset.currentPrice.toLocaleString()}
                </p>
                <p className="text-sm text-gray-400">Current Market Price</p>
            </div>

            <button 
                onClick={() => onBuy(asset)}
                className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 font-semibold active:scale-95 transition-transform"
            >
                Buy {asset.name}
            </button>
        </div>
    );
}

export default AssetCard;