import api from '.';

async function getMovementList(success, error) {
    await api.post(`/movement/list`).then(success).catch(error);
}

async function getBoxList(success, error) {
    await api.post(`/box/list`).then(success).catch(error);
}

async function saveWod(wod, success, error) {
    await api.post(`/wod/save`, wod).then(success).catch(error);
}

export {
    getMovementList,
    getBoxList,
    saveWod,
}