export interface ITechnicien {
  id?: number;
  nom?: string;
  prenom?: string;
  coordonnees?: string;
  numeroRue?: number;
  rue?: string;
  codePostal?: string;
  ville?: string;
}

export const defaultValue: Readonly<ITechnicien> = {};
