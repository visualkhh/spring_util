export interface Profile {
  id: null;
  name: string;
  pic: string;
}

export const DefaultProfile: Profile = {
  id: null,
  name: null,
  pic: "https://i.imgur.com/0ROzKWN.png",
};

export function createProfile(data): Profile {
  return {
    id: data.uid || data.user_id || DefaultProfile.id,
    name: data.displayName || data.name || DefaultProfile.name,
    pic: data.photoURL || data.picture || DefaultProfile.pic,
  };
}
