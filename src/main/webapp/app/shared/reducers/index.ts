import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import formateur, {
  FormateurState
} from 'app/entities/formateur/formateur.reducer';
// prettier-ignore
import stagiaire, {
  StagiaireState
} from 'app/entities/stagiaire/stagiaire.reducer';
// prettier-ignore
import technicien, {
  TechnicienState
} from 'app/entities/technicien/technicien.reducer';
// prettier-ignore
import gestionnaire, {
  GestionnaireState
} from 'app/entities/gestionnaire/gestionnaire.reducer';
// prettier-ignore
import module, {
  ModuleState
} from 'app/entities/module/module.reducer';
// prettier-ignore
import cursus, {
  CursusState
} from 'app/entities/cursus/cursus.reducer';
// prettier-ignore
import matiere, {
  MatiereState
} from 'app/entities/matiere/matiere.reducer';
// prettier-ignore
import ordinateur, {
  OrdinateurState
} from 'app/entities/ordinateur/ordinateur.reducer';
// prettier-ignore
import projecteur, {
  ProjecteurState
} from 'app/entities/projecteur/projecteur.reducer';
// prettier-ignore
import salle, {
  SalleState
} from 'app/entities/salle/salle.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly formateur: FormateurState;
  readonly stagiaire: StagiaireState;
  readonly technicien: TechnicienState;
  readonly gestionnaire: GestionnaireState;
  readonly module: ModuleState;
  readonly cursus: CursusState;
  readonly matiere: MatiereState;
  readonly ordinateur: OrdinateurState;
  readonly projecteur: ProjecteurState;
  readonly salle: SalleState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  formateur,
  stagiaire,
  technicien,
  gestionnaire,
  module,
  cursus,
  matiere,
  ordinateur,
  projecteur,
  salle,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
