import { IMatiere } from 'app/shared/model/matiere.model';
import { IModule } from 'app/shared/model/module.model';

export interface IFormateur {
  id?: number;
  nom?: string;
  prenom?: string;
  coordonnees?: string;
  numeroRue?: number;
  rue?: string;
  codePostal?: string;
  ville?: string;
  matieresDebutants?: IMatiere[];
  matieresIntermedaires?: IMatiere[];
  matieresAvances?: IMatiere[];
  matieresConfirmes?: IMatiere[];
  matieres?: IMatiere[];
  modules?: IModule[];
}

export const defaultValue: Readonly<IFormateur> = {};
