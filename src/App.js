import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

const App = () => {
  const [elevs, setElevs] = useState([]);
  const [name, setName] = useState('');
  const [telephone, setTelephone] = useState('');
  const [editId, setEditId] = useState(null);

  // Fetch all elevs on component mount
  useEffect(() => {
    fetchElevs();
  }, []);

  const fetchElevs = async () => {
    try {
      const response = await axios.get('http://localhost:8080/findAllElev'); // Correct API call
      setElevs(response.data);
    } catch (error) {
      console.error('Error fetching elevs:', error);
    }
  };

  const addOrUpdateElev = async () => {
    const newElev = { name, telephone };

    try {
      if (editId) {
        // Update elev
        await axios.put(`http://localhost:8080/updateElev/${editId}`, newElev); // Correct API call
      } else {
        // Create new elev
        await axios.post('http://localhost:8080/createElev', newElev); // Correct API call
      }
      setName('');
      setTelephone('');
      setEditId(null);
      fetchElevs(); // Refresh the list
    } catch (error) {
      console.error('Error creating/updating elev:', error);
    }
  };

  const deleteElev = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/deleteElev/${id}`); // Correct API call
      fetchElevs(); // Refresh the list
    } catch (error) {
      console.error('Error deleting elev:', error);
    }
  };

  const startEditElev = (elev) => {
    setName(elev.name);
    setTelephone(elev.telephone);
    setEditId(elev.id);
  };

  return (
    <div className="App">
      <h1>Elev Registration</h1>
      
      <div>
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          type="text"
          placeholder="Telephone"
          value={telephone}
          onChange={(e) => setTelephone(e.target.value)}
        />
        <button onClick={addOrUpdateElev}>
          {editId ? 'Update Elev' : 'Add Elev'}
        </button>
      </div>

      <h2>List of Elevs</h2>
      <ul>
        {elevs.map((elev) => (
          <li key={elev.id}>
            {elev.name} - {elev.telephone}
            <button onClick={() => startEditElev(elev)}>Edit</button>
            <button onClick={() => deleteElev(elev.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default App;
