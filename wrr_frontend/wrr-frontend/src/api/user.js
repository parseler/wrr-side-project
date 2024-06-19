import api from '.'

const uri = `/user`;

async function getUserInfo(accessToken, success, error) {
    await api.post(`${uri}/info`, accessToken).then(success).catch(error);
}

export {
    getUserInfo,
}