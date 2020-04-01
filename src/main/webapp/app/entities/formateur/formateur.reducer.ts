import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFormateur, defaultValue } from 'app/shared/model/formateur.model';

export const ACTION_TYPES = {
  FETCH_FORMATEUR_LIST: 'formateur/FETCH_FORMATEUR_LIST',
  FETCH_FORMATEUR: 'formateur/FETCH_FORMATEUR',
  CREATE_FORMATEUR: 'formateur/CREATE_FORMATEUR',
  UPDATE_FORMATEUR: 'formateur/UPDATE_FORMATEUR',
  DELETE_FORMATEUR: 'formateur/DELETE_FORMATEUR',
  RESET: 'formateur/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFormateur>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FormateurState = Readonly<typeof initialState>;

// Reducer

export default (state: FormateurState = initialState, action): FormateurState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FORMATEUR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FORMATEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FORMATEUR):
    case REQUEST(ACTION_TYPES.UPDATE_FORMATEUR):
    case REQUEST(ACTION_TYPES.DELETE_FORMATEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FORMATEUR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FORMATEUR):
    case FAILURE(ACTION_TYPES.CREATE_FORMATEUR):
    case FAILURE(ACTION_TYPES.UPDATE_FORMATEUR):
    case FAILURE(ACTION_TYPES.DELETE_FORMATEUR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FORMATEUR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FORMATEUR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FORMATEUR):
    case SUCCESS(ACTION_TYPES.UPDATE_FORMATEUR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FORMATEUR):
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

const apiUrl = 'api/formateurs';

// Actions

export const getEntities: ICrudGetAllAction<IFormateur> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FORMATEUR_LIST,
  payload: axios.get<IFormateur>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFormateur> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FORMATEUR,
    payload: axios.get<IFormateur>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFormateur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FORMATEUR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFormateur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FORMATEUR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFormateur> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FORMATEUR,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
