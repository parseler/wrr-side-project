import api from '.';

let uri = `/wod`

async function getMovementList(success, error) {
    await api.post(`${uri}/movement/list`).then(success).catch(error);
};

async function getBoxList(success, error) {
    await api.post(`${uri}/box/list`).then(success).catch(error);
};

async function getWorkoutTypeList(success, error) {
    await api.post(`${uri}/type/list`).then(success).catch(error);
}

async function saveWod(wod, success, error) {
    await api.post(`${uri}/save`, wod).then(success).catch(error);
};

async function getRandomWod(success, error) {
    await api.post(`${uri}/random`).then(success).catch(error);
}

export {
    getMovementList,
    getBoxList,
    getWorkoutTypeList,
    saveWod,
    getRandomWod,
};