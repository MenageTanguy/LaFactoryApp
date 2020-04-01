import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProjecteur, defaultValue } from 'app/shared/model/projecteur.model';

export const ACTION_TYPES = {
  FETCH_PROJECTEUR_LIST: 'projecteur/FETCH_PROJECTEUR_LIST',
  FETCH_PROJECTEUR: 'projecteur/FETCH_PROJECTEUR',
  CREATE_PROJECTEUR: 'projecteur/CREATE_PROJECTEUR',
  UPDATE_PROJECTEUR: 'projecteur/UPDATE_PROJECTEUR',
  DELETE_PROJECTEUR: 'projecteur/DELETE_PROJECTEUR',
  RESET: 'projecteur/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProjecteur>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ProjecteurState = Readonly<typeof initialState>;

// Reducer

export default (state: ProjecteurState = initialState, action): ProjecteurState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROJECTEUR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROJECTEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROJECTEUR):
    case REQUEST(ACTION_TYPES.UPDATE_PROJECTEUR):
    case REQUEST(ACTION_TYPES.DELETE_PROJECTEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROJECTEUR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROJECTEUR):
    case FAILURE(ACTION_TYPES.CREATE_PROJECTEUR):
    case FAILURE(ACTION_TYPES.UPDATE_PROJECTEUR):
    case FAILURE(ACTION_TYPES.DELETE_PROJECTEUR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROJECTEUR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROJECTEUR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROJECTEUR):
    case SUCCESS(ACTION_TYPES.UPDATE_PROJECTEUR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROJECTEUR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/projecteurs';

// Actions

export const getEntities: ICrudGetAllAction<IProjecteur> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROJECTEUR_LIST,
  payload: axios.get<IProjecteur>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IProjecteur> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROJECTEUR,
    payload: axios.get<IProjecteur>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProjecteur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROJECTEUR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProjecteur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROJECTEUR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProjecteur> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROJECTEUR,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
