import api from '.';

const uri = `/auth`;

async function reissue(success, error) {
    await api.post(`${uri}/reissue`).then(success).catch(error);
}

export {
    reissue,
}