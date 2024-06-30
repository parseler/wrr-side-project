import api from '.';

let url = `/wod`

async function getMovementList(success, error) {
    await api.post(`${url}/movement/list`).then(success).catch(error);
};

async function getBoxList(success, error) {
    await api.post(`${url}/box/list`).then(success).catch(error);
};

async function getWorkoutTypeList(success, error) {
    await api.post(`${url}/type/list`).then(success).catch(error);
}

async function saveWod(wod, success, error) {
    await api.post(`${url}/save`, wod).then(success).catch(error);
};

export {
    getMovementList,
    getBoxList,
    getWorkoutTypeList,
    saveWod,
};