import { IOrdinateur } from 'app/shared/model/ordinateur.model';
import { ICursus } from 'app/shared/model/cursus.model';

export interface IStagiaire {
  id?: number;
  nom?: string;
  prenom?: string;
  coordonnees?: string;
  numeroRue?: number;
  rue?: string;
  codePostal?: string;
  ville?: string;
  ordinateur?: IOrdinateur;
  cursuses?: ICursus[];
}

export const defaultValue: Readonly<IStagiaire> = {};
