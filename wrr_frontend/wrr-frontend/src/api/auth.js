import api from '.';

const uri = `/auth`;

async function reissue(success, error) {
    await api.post(`${uri}/reissue`).then(success).catch(error);
}

async function isValid(success, error) {
    await api.post(`${uri}/valid`).then(success).catch(error);
}

async function logout(success, error) {
    await api.post(`${uri}/logout`).then(success).catch(error);
}

export {
    reissue,
    isValid,
    logout,
}