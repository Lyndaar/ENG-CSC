QrScanner.WORKER_PATH = 'https://cdn.jsdelivr.net/npm/qr-scanner/qr-scanner-worker.min.js';

document.addEventListener('DOMContentLoaded', () => {
    const videoElem = document.getElementById('reader');
    const qrTableBody = document.getElementById('qrTable').querySelector('tbody');
    const saveButton = document.getElementById('saveButton');
    const startScannerButton = document.getElementById('startScannerButton');

    let qrScanner = new QrScanner(videoElem, result => {
        alert(`QR Code scanned: ${result}`);
        qrScanner.stop(); // stop scanning after successful scan

        let qrData;
        try {
            qrData = parseQRCodeData(result);
        } catch (error) {
            alert('Invalid QR Code data');
            return;
        }

        displayScannedData(qrData);
    });

    startScannerButton.addEventListener('click', () => {
        console.log('Start Scanner button clicked');
        qrScanner.start().then(() => {
            console.log('QR scanner started');
        }).catch(err => {
            console.error('Unable to start QR scanner:', err);
        });
    });

    function parseQRCodeData(data) {
        const qrData = {};

        // Adjusting parsing logic to match the QR code format
        const parts = data.match(/Producer Name: (.*?) Batch Number: (.*?) Product Type: (.*?) Product Item: (.*?) Production Date: (.*?) Expiry Date: (.*)/);

        if (parts && parts.length === 7) {
            qrData.producerName = parts[1];
            qrData.batchNo = parts[2];
            qrData.productType = parts[3];
            qrData.productItem = parts[4];
            qrData.productionDate = parts[5];
            qrData.expiryDate = parts[6];
        } else {
            throw new Error('Invalid QR code format');
        }

        return qrData;
    }

    function displayScannedData(data) {
        const row = qrTableBody.insertRow();
        row.insertCell(0).textContent = data.producerName || '';
        row.insertCell(1).textContent = data.batchNo || '';
        row.insertCell(2).textContent = data.productType || '';
        row.insertCell(3).textContent = data.productItem || '';
        row.insertCell(4).textContent = data.productionDate || '';
        row.insertCell(5).textContent = data.expiryDate || '';

        // Add location dropdown
        const locationCell = row.insertCell(6);
        const locationSelect = document.createElement('select');
        ['CSC General', 'CSC Commissary - Agungi', 'CSC Commissary - Wuse', 'CSC WIP-WUSE',
            'CSC WIP AGUNGI', 'CSC Kitchen PH', 'CSC Commissary Kano - Kitchen', 'CSC COM PH ( OBJ Way)',
            'CSC Commissary Kano', 'CSC Commissary Magboro', 'CSC General', 'CSC COM PH ( OBJ Way)',
            'CSC KITCHEN PH', 'CSC Commissary Ibadan', 'CSC Commissary Ibadan - Kitchen', 'CSC Saka',
            'CSC Admiralty Lekki 1', 'CSC Palms Lekki 1', 'CSC Polo Park Enugu', 'CSC Toyin Ikeja',
            'CSC Aos Surulere', 'CSC Ogunnaike Ikeja', 'CSC Calcutta Apapa', 'CSC Ring Road Ibadan',
            'CSC Palms Ibadan', 'CSC 1st Avenue Festac', 'CSC Agungi Lekki 2', 'CSC ICM Ikeja',
            'CSC Aminukano Wuse 2', 'CSC Gwarinpa', 'CSC Ajah', 'CSC Yaba', 'CSC Agidingbi', 'CSC Ogudu',
            'CSC Ikotun', 'CSC Okota', 'CSC Ikorodu', 'CSC Ilupeju', 'CSC IDIMU', 'CSC OKO OBA',
            'CSC GARKI', 'CSC Abeokuta', 'CSC IKOYI', 'CSC Satellite Town', 'CSC PHC1 (Olusegun Obasanjo Way)',
            'CSC JABI LAKE MALL', 'CSC Maryland', 'CSC Otta', 'CSC Sangotedo', 'CSC Novare Gate Way mall , abuja',
            'CSC IJU', 'CSC Magodo', 'CSC ITIRE', 'CSC Bodija', 'CSC-Apo Mall', 'CSC Akure Mall', 'CSC Kubwa',
            'CSC EGBEDA', 'CSC Kwara Mall', 'CSC - Ajao', 'CSC PH2 (PH Aba Exp Way)', 'CSC MIRIAN RD. CALABAR',
            'CSC PH4 (Peter Odili Road)', 'CSC Simbiat Mall Ikeja', 'CSC -Badore', 'CSC- Gbagada',
            'CSC Uyo - Ikot Ekpene Rd', 'CSC Kano Bompai Road', 'CSC Factory Road, Aba', 'CSC Okpanam Rd Asaba',
            'CSC Aggrey road PH', 'CSC Edo Sapele Rd Benin', 'CSC Sapele Rd Warri', 'CSC Benin Ugbowo',
            'CSC Zoo Rd, Kano', 'CSC Challenge Ibadan', 'CSC Nyanya, Abuja', 'CSC Choba PH',
            'CSC Barnawa Kaduna State', 'CSC Ali-Akilu', 'CSC Niger - Minna', 'CSC Aromire', 'CSC Jakande',
            'CSC Admiralty 2', 'CSC Bode Thomas', 'CSC Yenegoa - Mbiama', 'CSC Akobo, Ibadan',
            'CSC MKO Abiola Way, Abeokuta', 'CSC Jara Mall, Benin', 'CSC AWKA', 'CSC Banana Island',
            'CSC Ajose', 'CSC Owerri Concord Avenue', 'CSC Jos', 'CSC Lasu Iba, Lagos', 'CSC ONITSHA',
            'CSC Ikorodu 2', 'CSC Lagos Awoyaya', 'CSC Freedom way', 'CSC PRIME MALL, ORCHID RD',
            'CSC VGC-Ikota', 'Cash Imprest CSC Training', 'CSC Abeokuta', 'CSC PHC', 'CSC PH2 ABA Exp Way',
            'CSC ISHERI RD/OMOLE PH2', 'CSC IPAJA', 'CSC Surulre 2', 'CSC-Akoka', 'CSC Lekki Roundabout',
            'CSC Awolowo Way Ibadan', 'CSC Gado Nasko Kubwa', 'CSC Wetheral Road Owerri', 'CSC Airport Road Warri',
            'CSC Akure', 'CSC Independent Layout Enugu', 'CSC Lekki Roundabout', 'CSC ABAK RD UYO',
            'CSC Airport Road Benin', 'CSC Ikot Ekpene Road', 'CSC MARIAN RD.CALABAR', 'CSC Ejigbo',
            'CSC Grand Square', 'CSC Badore', 'CSC MMA', 'CSC Grand Square Mall', 'CSC Onitsha Mall',
            'CSC Benin - Airport Rd', 'CSC Keffi', 'CSC Kaduna - Ahmadu Bello', 'CSC PH3 (NTARoad PH)',
            'CSC Lagos Island', 'CSC Kano Ado Bayero', 'CSC RUMOKORO PH', 'CSC COM Saka', 'Mobile Cart-CSC',
            'Supermarket-CSC', 'CSC Abia-Umuahia', 'CSC SAGAMU', 'CSC Gbagada', 'CSC Imo Owerri',
            'CSC Factory Rd Aba', 'CSC Marina', 'CSC Airport road, Kano', 'CSC Isheri, Lagos',
            'CSC PH 3 (NTA Road PH)', 'CSC Ipaja', 'CSC Ado-Ekiti', 'CSC Ceddi mall Abuja', 'CSC Airport Rd. Abuja',
            'CSC Wuse zone 4 Abuja', 'CSC Oniru Water Corporation, Lagos', 'CSC Abakaliki Mall, Ebonyi',
            'CSC Yakubu Gowon Way, Abuja', 'CSC Uwani, Enugu', 'CSC Olowoeko - Lekki/Oniru, Lagos', 'CSC MM2',
            'CSC Shomolu', 'CSC Imo', 'CSC Nnebisi Asaba', 'CSC Ada George Rd, NTA PH', 'CSC Abuja Novare Mall Central Area',
            'CSC Lekki chevron drive', 'CSC OSUN MALL', 'CSC GBONGON, OSHOGBO', 'CSC EBUTE METTA, YABA',
            'CSC ABUJA DOMESTIC AIRPORT', 'CSC SAPELE, WARRI', 'CSC PH MALL', 'CSC SULTAN ABUBAKAR WAY, ABUJA',
            'CSC Ibafo', 'CSC MMA1', 'CSC Sapele - Sapele', 'CSC Lagos Island - Marina Broad Street',
            'CSC Gateway Abuja 2 - Airport Rd', 'CSC Asokoro - Abuja', 'CSC - UAC Foods', 'CSC UAC Foods - Kitchen'
        ].forEach(location => {
            const option = document.createElement('option');
            option.value = location;
            option.textContent = location;
            locationSelect.appendChild(option);
        });
        locationCell.appendChild(locationSelect);

        // Add quantity input
        const quantityCell = row.insertCell(7);
        const quantityInput = document.createElement('input');
        quantityInput.type = 'number';
        quantityInput.min = '0';
        quantityInput.value = '0';
        quantityCell.appendChild(quantityInput);

        // Add vehicle number input
        const vehicleNumberCell = row.insertCell(8);
        const vehicleNumberInput = document.createElement('input');
        vehicleNumberInput.type = 'text';
        vehicleNumberCell.appendChild(vehicleNumberInput);

        // Add delete button
        const actionsCell = row.insertCell(9);
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', () => {
            row.remove();
        });
        actionsCell.appendChild(deleteButton);
    }

    saveButton.addEventListener('click', () => {
        const rows = qrTableBody.rows;
        const dataToSave = [];

        for (let i = 0; i < rows.length; i++) {
            const cells = rows[i].cells;
            const qrData = {
                producerName: cells[0].textContent,
                batchNo: cells[1].textContent,
                productType: cells[2].textContent,
                productItem: cells[3].textContent,
                productionDate: cells[4].textContent,
                expiryDate: cells[5].textContent,
                location: cells[6].querySelector('select').value,
                quantity: cells[7].querySelector('input').value, // Include quantity
                vehicleNumber: cells[8].querySelector('input').value // Include vehicle number
            };
            dataToSave.push(qrData);
        }

        fetch('/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataToSave)
        }).then(response => {
            if (response.ok) {
                alert('Data saved successfully!');
            } else {
                return response.text().then(text => { throw new Error(text); });
            }
        }).catch(error => {
            console.error('Error:', error);
            alert('Error saving data: ' + error.message);
        });
    });
});