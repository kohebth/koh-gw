<div class="form-group row">
    {{ html()->label('IP Address <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('ip') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="thirdPartyBlockedIP.ip" class="form-control">
        @error('thirdPartyBlockedIP.ip')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>

<div class="form-group row">
    {{ html()->label('Name')->class('col-sm-2 form-control-label')->for('name') }}
    <div class="col-sm-10">
        <select wire:model.defer="thirdPartyBlockedIP.name" name="name" class="form-control">
            <option value=""></option>
            @foreach($nameOptions as $name)
                <option value="{{$name}}">{{$name}}</option>
            @endforeach
        </select>
        @error('thirdPartyBlockedIP.name')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
