import { ICursus } from 'app/shared/model/cursus.model';

export interface IGestionnaire {
  id?: number;
  nom?: string;
  prenom?: string;
  coordonnees?: string;
  numeroRue?: number;
  rue?: string;
  codePostal?: string;
  ville?: string;
  cursuses?: ICursus[];
}

export const defaultValue: Readonly<IGestionnaire> = {};
